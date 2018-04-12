package ee.ttu.iti0202.stargate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class Space {

    public List<Planet> csvDataToPlanets(String filePath){

        List<Planet> planets = new ArrayList<>();

        BufferedReader br = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {

                String[] planet = line.split(",");

                planets.add(new PlanetBuilder(planet[0], Long.valueOf(planet[1]),
                        Boolean.valueOf(planet[2]),
                        Boolean.valueOf(planet[3]),
                        Arrays.stream(planet[4].split(";")).collect(Collectors.toList())).build());

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
        return planets.stream().filter(
                planet -> planet.getTeamVisited().size() == 0)
                .filter(Planet::isDhdhAvailable)
                .filter(Planet::isStargateAvailable)
                .collect(Collectors.toSet());
    }

    public OptionalDouble getAvgInhabitantsOnPlanetsWithStargate(List<Planet> planets) {
        return planets.stream()
                .filter(Planet::isStargateAvailable)
                .map(Planet::getInhabitants)
                .collect(Collectors.averagingDouble(Planet::getInhabitants));

    }

    public List<String> getTeamsWhoHaveVisitedSmallNotDeadPlanets(List<Planet> planets) {
        List<String> teams;

        return planets.stream()
                .filter(planet -> planet.getInhabitants() != 0)
                .filter(planet -> planet.getInhabitants() < 2500)
                .map(Planet::getTeamVisited)
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

    }

    public Map<String, Long> getCodeNameClassifierFrequency(List<Planet> planets) {
        return planets.stream()
                .map(Planet::getName)
                .map(name -> name.substring(0, 2))
                .collect(Collectors.toMap(s -> s, s -> (long) 1, Long::sum));
    }

    public static void main(String[] args) throws IOException {

        Space space = new Space();
        String filePath = "EX10Stargate/src/ee/ttu/iti0202/stargate/space/planets_data.csv";
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
