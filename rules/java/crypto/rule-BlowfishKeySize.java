// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

public class BlowfishKeySize {
    public void danger() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
        // ruleid: java_crypto_rule-BlowfishKeySize
        keyGen.init(64);
    }
}
