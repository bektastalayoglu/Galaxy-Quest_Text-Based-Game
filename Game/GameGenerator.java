package Game;

import CelestialBody.Moon;
import CelestialBody.Planet;
import Inventory.Inventory;
import Items.Item;
import Misc.Location;
import Player.Player;
import SpaceShip.*;

import java.util.*;

/**
 * The GameGenerator class generates initial game entities such as planets, player, mother ship, space pod,
 * and destination.
 *
 *  , Bektas Talayoglu
 */
public class GameGenerator {

    /**
     * This method generates a set of planets with items and moons
     *
     * @return This method returns set of planets in the game.
     *  ,  
     */
    public static Set<Planet> generatePlanets() {
        // Create empty container for planets
        Set<Planet> planets = new HashSet<>();

        // Hashmap for items used both in mother ship and planets
        HashMap<String, Item> items = generateItems();


        // Create planets for the game
        Planet stormyPowerPlanet = new Planet(
                "StormyPower",
                2,
                new Location(-1, -0),
                new ArrayList<>(),
                new HashSet<>(List.of(items.get("battery"))),
                "\n" +
                        "On a scary planet, there are always strong electromagnetic storms. " +
                        "Somewhere in the stormy air, there's a hidden item. " +
                        "Players need to face the storms, find where the item is, and grab it."
        );

        Planet lavaPlanet = new Planet(
                "Lava",
                2,
                new Location(1, -1),
                new ArrayList<>(),
                new HashSet<>(List.of(items.get("fuel"))),
                "\n" +
                        "A planet with lots of lava and volcanoes. " +
                        "It is a planet where explorers used to visit and found its fuels to be of high quality."
                        + "\n" + "Now it's under lava. Be careful of the volcano mountains, search the planet's " +
                        "surface. " + "\n" + "Maybe there is what you need for your ship hidden there."
        );
/*        HashSet<Item> itemss = new HashSet<>();;
        itemss.add(items.get("solarPanel"));*/
        Planet gardenPlanet = new Planet(
                "Garden",
                3,
                new Location(2, 2),
                new ArrayList<>(),
                new HashSet<>(List.of(items.get("solarPanel"))),
                "\n" +
                        "A planet covered in vast sunflower meadows. Explore the sunflower fields to find " + "\n" +
                        "a facility where advanced solar panels are developed and stored."
        );

//       Create the moons of the planets
        Moon retroMoon = new Moon(
                "Retro",
                1,
                new Location(0, 0),
                stormyPowerPlanet,
                new HashSet<>(List.of(items.get("chip"))),
                " Retro Moon, which is an old place with cool tech stuff. " + "\n" +
                        "You need to fly through the storms " + "\n" +
                        "and explore the RetroTech Moon to find things that can fix your spaceship"
        );

        Moon crystalMoon = new Moon(
                "Crystal",
                1,
                new Location(-1, -1),
                lavaPlanet,
                new HashSet<>(List.of(items.get("speedBooster"))),
                " A moon glows with bright crystals. " + "\n" +
                        "The thing that makes your ship faster is somewhere on its surface. " + "\n" +
                        "Navigate its surface and find the hidden item."
        );


        // Add moons to planets
        stormyPowerPlanet.addMoon(retroMoon);
        lavaPlanet.addMoon(crystalMoon);

        // Add planets to the container
        planets.add(stormyPowerPlanet);
        planets.add(lavaPlanet);
        planets.add(gardenPlanet);


        return planets;
    }

    /**
     * This method generates a player.
     *
     * @return It returns a player
     *  
     */
    public static Player generatePlayer(String name) {
        return new Player(
                name,
                new Inventory(),
                GameGenerator.generateMotherShip(),
                false
        );
    }

    /**
     * This method generates a mother ship.
     *
     * @return It returns a mother ship object
     *  
     */
    public static MotherShip generateMotherShip() {
        HashMap<Item, Boolean> items = new HashMap<>();

        //Adding all the items into the hashmao and setting them as not working/False
        for (Map.Entry<String, Item> entry : generateItems().entrySet()) {
            items.put(entry.getValue(), false);
        }
        return new MotherShip(
                "Mothership",
                items,
                false
        );
    }

    /**
     * This method generates a space pod.
     *
     * @return It returns a space pod
     *  
     */
    public static SpacePod generateSpacePod() {
        return new SpacePod(
                "SpacePod",
                new Location()
        );
    }

    /**
     * This method generates a destination planet (Earth).
     *
     * @return It returns destination planet.
     *  
     */
    public static Planet generateDestination() {
        return new Planet(
                "Earth",
                64000,
                new Location(),
                null,
                null,
                "Home sweet home"
        );
    }

    /**
     * Generate items hash map.
     *
     * @return the hash map
     *  , Bektas Talayoglu
     */
    public static HashMap<String, Item> generateItems() {
        HashMap<String, Item> items = new HashMap<>();

        Item battery = new Item("Battery",
                "It is one of the most important items and your ship cannot provide its energy without it.",
                new Location(1, 2));

        Item chip = new Item("Chip", "You cannot find our way without this, " +
                "the most important part of the navigation device.",
                new Location(1, 0));

        Item speedBooster = new Item("Booster", "Increases your spaceship's " +
                "speed. " + "Without this you can't travel very far from this abandoned place. ",
                new Location(0, -1));

        Item fuel = new Item("Fuel", "This will help you to work your mother ship" +
                " engine." + " Without this your mother ship will never work.",
                new Location(1, 1));

        Item solarPanel = new Item("Panel", "These gather sunlight to power up " +
                "your spaceship. " + "It is missing in your mother ship. Find it on sunny planets ",
                new Location(1, 0));

        items.put("battery", battery);
        items.put("chip", chip);
        items.put("speedBooster", speedBooster);
        items.put("fuel", fuel);
        items.put("solarPanel", solarPanel);

        return items;
    }
}
