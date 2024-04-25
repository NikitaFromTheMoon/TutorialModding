package net.bored_moon.examplemod.client;

public class ClientMadnessData {
    private static float playerMadness;

    public static void set(float madness) {

        ClientMadnessData.playerMadness = madness;
    }

    public static float getPlayerMadness() {
        return playerMadness;
    }
}
