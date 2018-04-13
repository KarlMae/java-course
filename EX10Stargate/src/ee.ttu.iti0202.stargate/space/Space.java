package ee.ttu.iti0202.stargate.space;

import ee.ttu.iti0202.stargate.planet.Planet;
import ee.ttu.iti0202.stargate.planet.PlanetBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class Space {

    //Convert csv file to data
    public List<Planet> csvDataToPlanets(String filePath) {

        List<Planet> planets = new ArrayList<>();

        BufferedReader br = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] planet = line.split(",");


                planets.add(new PlanetBuilder(planet[0], Long.valueOf(planet[1]),
                        Boolean.valueOf(planet[2]),
                        Boolean.valueOf(planet[3]),
                        List.of(planet[4]
                                .replace("[", "")
                                .replace("]", "")
                                .replace(" ", "")
                                .split(";")).stream()
                                .filter(s -> !s.equals(""))
                                .collect(Collectors.toList()))
                        .build());

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return planets;
    }

    public Set<Planet> getDeadPlanets(List<Planet> planets) {
        return planets.stream()
                .filter(planet -> planet.getInhabitants() == 0)
                .collect(Collectors.toSet());
    }

    public Set<Planet> needToVisit(List<Planet> planets) {
        return planets.stream()
                .filter(planet -> planet.getTeamVisited().isEmpty())
                .filter(Planet::isDhdhAvailable)
                .filter(Planet::isStargateAvailable)
                .collect(Collectors.toSet());
    }


    public OptionalDouble getAvgInhabitantsOnPlanetsWithStargate(List<Planet> planets) {
        return planets.stream()
                .filter(Planet::isStargateAvailable)
                .mapToDouble(planet -> (double) planet.getInhabitants())
                .average();
    }

    public List<String> getTeamsWhoHaveVisitedSmallNotDeadPlanets(List<Planet> planets) {
        return planets.stream()
                .filter(planet -> planet.getInhabitants() != 0)
                .filter(planet -> planet.getInhabitants() < 2500)
                .map(Planet::getTeamVisited)
                .flatMap(Collection::stream)
                .filter(s -> !s.equals(""))
                .distinct()
                .sorted(Comparator.comparing(o -> Integer.valueOf(o.split("-")[1])))
                .collect(Collectors.toList());

    }


    // Küsi miks siin substring 0-3 annab kolm esimest tähte
    public Map<String, Long> getCodeNameClassifierFrequency(List<Planet> planets) {
        return planets.stream()
                .map(Planet::getName)
                .filter(s -> s.matches("P..-..."))
                .map(name -> name.substring(0, 3))
                .sorted()
                .collect(Collectors.toMap(s -> s, s -> (long) 1, Long::sum));
    }

    public static void main(String[] args) throws IOException {

        Space space = new Space();
        String filePath = "C:\\Users\\karl_\\Desktop\\VõrkSpeiss\\Java\\Tund\\iti0202\\EX10Stargate\\src\\ee.ttu" +
                ".iti0202.stargate\\space\\planets_data.csv";
        List<Planet> planets = space.csvDataToPlanets(filePath);

        System.out.println(space.getDeadPlanets(planets).size());  // 70

        OptionalDouble avg = space.getAvgInhabitantsOnPlanetsWithStargate(planets);
        if (avg.isPresent()) {
            System.out.printf("Avg: %.0f\n", avg.getAsDouble());  // Avg: 186978984
        }

        System.out.println(space.needToVisit(planets));
    /*
    [Dakara, Earth, Gadmeer homeworld, Latona, M6R-867, Orin's planet, P3A-194,
     P3K-447, P3L-997, P3X-584, P4M-399, P4X-636, P8X-412, Retalia, Sartorus, Sudaria]
    */

        System.out.println(space.getCodeNameClassifierFrequency(planets).size());  // 39

        System.out.println(space.getTeamsWhoHaveVisitedSmallNotDeadPlanets(planets));  // [sg-1, sg-4, sg-9, sg-15]
    }

}
