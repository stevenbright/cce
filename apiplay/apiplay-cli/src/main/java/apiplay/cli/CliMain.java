package apiplay.cli;

import apiplay.cli.alg.NbSha256;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Entry point.
 */
public final class CliMain {

  public static void main(String[] args) throws Exception {
    System.out.println("Digest demo");

    // 1312AF178C253F84028D480A6ADC1E25E81CAA44C749EC81976192E2EC934C64
    demoSha256("Hello, world!0");

    // E9AFC424B79E4F6AB42D99C81156D3A17228D6E1EEF4139BE78E948A9332A7D8
    demoSha256("Hello, world!1");
  }

  //
  // Private
  //

  private static void demoSha256(String input) {
    final byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
    final String officialDigest;
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA-256");
      final byte[] result = digest.digest(inputBytes);
      officialDigest = DatatypeConverter.printHexBinary(result);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }

    final NbSha256 nbSha256 = new NbSha256();
    final String altDigest = DatatypeConverter.printHexBinary(nbSha256.digest(inputBytes));

    System.out.println("Digest for string='" + input + "':\n" +
        "\tOfficial: " + officialDigest + "\n" +
        "\tAlt:      " + altDigest);
  }
}
