package ee.ttu.iti0202.justice;

import ee.ttu.iti0202.justice.archive.Archive;
import ee.ttu.iti0202.justice.exceptions.PersonTooYoungException;
import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.humans.CitizenBuilder;
import ee.ttu.iti0202.justice.humans.Judge;
import ee.ttu.iti0202.justice.institutions.Court;
import ee.ttu.iti0202.justice.institutions.Prison;
import ee.ttu.iti0202.justice.institutions.Station;
import ee.ttu.iti0202.justice.judgements.Acquital;
import ee.ttu.iti0202.justice.judgements.Conviction;
import ee.ttu.iti0202.justice.judgestrategies.PervJudgeStrategy;
import ee.ttu.iti0202.justice.judgestrategies.ToughJudgeStrategy;
import ee.ttu.iti0202.justice.town.Town;
import ee.ttu.iti0202.justice.town.TownBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Citizen> citizens = new ArrayList<>();
        Archive archive = Archive.of(citizens);
        Court court = new Court(archive);
        Station station = new Station();
        Prison prison = new Prison();

        TownBuilder townBuilder = new TownBuilder()
                .setCourt(court)
                .setPrison(prison)
                .setStation(station)
                .setName("Tallinn");

        Town tallinn = townBuilder.createTown();

        try {

            File inputF = new File("C:\\Users\\karl_\\IdeaProjects\\iti0202\\EX14Justice\\src\\ee.ttu.iti0202.justice\\names.txt");

            InputStream inputFS = new FileInputStream(inputF);

            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            Random random = new Random();
            citizens = br.lines().map(name -> {
                    name = name.replaceAll(" ", "");
                    name = name.replaceAll("\t", "");
                Citizen citizen = null;
                try {
                    citizen = new CitizenBuilder()
                            .setFirstName(name)
                            .setLastName("Kohilast")
                            .setAge(random.nextInt(80))
                            .createCitizen();
                } catch (PersonTooYoungException e) {
                    e.printStackTrace();
                }

                tallinn.getStations().get(0).addCitizen(citizen);
                return citizen;
            })
            .collect(Collectors.toList());

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(citizens);

        try {
            court.addJudge((Judge)new CitizenBuilder()
                    .setFirstName("Igor")
                    .setLastName("Mang")
                    .setAge(60)
                    .setProfession(Citizen.Profession.JUDGE)
                    .setStrategy(new PervJudgeStrategy())
                    .createCitizen());

            court.addJudge((Judge)new CitizenBuilder()
                    .setFirstName("Rando")
                    .setLastName("Lawyer")
                    .setAge(36)
                    .setProfession(Citizen.Profession.JUDGE)
                    .createCitizen());

            court.addJudge((Judge)new CitizenBuilder()
                    .setFirstName("Toomas")
                    .setLastName("Hendrik")
                    .setAge(45)
                    .setProfession(Citizen.Profession.JUDGE)
                    .setStrategy(new ToughJudgeStrategy())
                    .createCitizen());

            court.addJudge((Judge)new CitizenBuilder()
                    .setFirstName("Jaanus")
                    .setLastName("Vahkra")
                    .setAge(36)
                    .setProfession(Citizen.Profession.JUDGE)
                    .setStrategy(new ToughJudgeStrategy())
                    .createCitizen());

            court.addJudge((Judge)new CitizenBuilder()
                    .setFirstName("Timo")
                    .setLastName("Terve")
                    .setAge(36)
                    .setProfession(Citizen.Profession.JUDGE)
                    .setStrategy((citizen, lawyer, lawsuit, litigation) -> {
                        if (lawyer.getLawsuitsWon() > 5) {
                            return new Acquital();
                        } else {
                            return new Conviction(lawsuit.getMinimumFine(), lawsuit.getMinimumTimeToServe(), litigation);
                        }
                    })
                    .createCitizen());

        } catch (PersonTooYoungException ex) {
            System.out.println("Too young person tried to take power");
        }

        System.out.println(tallinn.getCourts().get(0).getJudges());


        for (int i = 0; i < 50; i++) {
            station.accuseSomeone(court, tallinn);
        }

        System.out.println(prison.getPrisoners());

    }
}
