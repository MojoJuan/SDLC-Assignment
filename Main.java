
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;

/**
 * SDLC Pt2. Assignment
 * @author moise
 */

/**
 * creating the main class
 */

public class Main {

  /**
   * creates the UI first before going on to the Word Occurrences portion
   * 
   * @param args
   *             documenting argument for the main method.
   * @throws IOException
   *                     throwing the exception to the file not being found
   */

  public static void main(String[] args) throws IOException {

    // Creating the Frame
    JFrame frame = new JFrame("Chat Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);

    // Creating the MenuBar and adding components
    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("FILE");
    JMenu m2 = new JMenu("Help");
    mb.add(m1);
    mb.add(m2);
    JMenuItem m11 = new JMenuItem("Open");
    JMenuItem m22 = new JMenuItem("Save as");
    m1.add(m11);
    m1.add(m22);

    // Creating the panel at bottom and adding components
    JPanel panel = new JPanel(); // the panel is not visible in output
    JLabel label = new JLabel("Enter Text");
    JTextField tf = new JTextField(10);// accepts upto 10 characters

    JButton send = new JButton("Send");
    JButton reset = new JButton("Reset");
    panel.add(label); // Components Added using Flow Layout
    panel.add(tf);
    panel.add(send);
    panel.add(reset);

    // Text Area at the Center
    // JTextArea ta = new JTextArea();

    JTextArea textArea = new JTextArea("Welcome to the Word Occurances Program.");
    textArea.setFont(new Font("Serif", Font.ITALIC, 16));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);

    // Adding Components to the frame.
    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, textArea);
    frame.setVisible(true);

    BufferedReader bufferedReader = new BufferedReader(new FileReader("textFile.txt"));

    // creating an empty map used to store word and frequencies of all words
    Map<String, Integer> wordCounts = new HashMap<>();

    String line;

    while ((line = bufferedReader.readLine()) != null) {

      // splitting line by use regular expression
      String[] words = line.split("[\\s;,?:!()\"]+");

      // iterate all words
      for (String word : words) {

        word = word.trim();

        if (word.length() > 0) {

          if (wordCounts.containsKey(word)) {
            wordCounts.put(word, wordCounts.get(word) + 1);
          } else {
            wordCounts.put(word, 1);
          }
        }
      }
    }

    // sorting wordCounts by frequency
    Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
        .sorted(Collections.reverseOrder(Entry.comparingByValue()))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    // printing word and frequencies of all words

    for (Map.Entry<String, Integer> entry : sortedWordCounts.entrySet()) {

      System.out.printf("%-20s%10s\n", entry.getKey(), entry.getValue());

    }
    bufferedReader.close();
  }
  /*
   * static String poem;
   * 
   * @Test
   * public void testWordChamber() {
   * int n = getStringCountInPoem("chamber");
   * Assert.assertEquals(n, 11);
   * }
   * 
   * @Test
   * public void testWordImplore() {
   * int n = getStringCountInPoem("implore");
   * Assert.assertEquals(n, 3);
   * }
   * 
   * @Test
   * public void testWordQuoth() {
   * int n = getStringCountInPoem("Quoth");
   * Assert.assertEquals(n, 5);
   * }
   * 
   * @Test
   * public void testWordRaven() {
   * int n = getStringCountInPoem("Raven");
   * Assert.assertEquals(n, 10);
   * }
   * 
   * @Test
   * public void testWordDoor() {
   * int n = getStringCountInPoem("door");
   * Assert.assertEquals(n, 14);
   * }
   * 
   * @Test
   * public void testWordRapping() {
   * int n = getStringCountInPoem("rapping");
   * Assert.assertEquals(n, 3);
   * }
   * 
   * @Test
   * public void testWordCenser() {
   * int n = getStringCountInPoem("censer");
   * Assert.assertEquals(n, 1);
   * }
   * 
   * @Test
   * public void testWordLamp_light() {
   * int n = getStringCountInPoem("lamp-light");
   * Assert.assertEquals(n, 3);
   * }
   * 
   * // it will return count from the poem that contains the count and word
   * 
   * public int getStringCountInPoem(String s) {
   * for (String k : poem.split("\n")) {
   * if (k.contains(s)) {
   * return Integer.parseInt(k.split(" ")[0]);
   * }
   * }
   * return 0;
   * }
   */
}