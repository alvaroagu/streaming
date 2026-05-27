public class PlaybackTester {
    public static void main(String[] args) {
        String[] files = new String[] {
            "videos/@2 AM COFFEE - A short film _ Sony FX3 _ 4K.mp4",
            "videos/sample2.mp4",
            "videos/sample1.mp4"
        };
        boolean allExist = true;
        for (String f : files) {
            java.io.File file = new java.io.File(f);
            System.out.println(f + " exists=" + file.exists());
            if (!file.exists()) allExist = false;
        }
        boolean desktop = java.awt.Desktop.isDesktopSupported();
        System.out.println("Desktop supported=" + desktop);
        System.exit(allExist && desktop ? 0 : 1);
    }
}
