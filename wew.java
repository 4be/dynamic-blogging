public static  PrivateKey getPemPrivateKey(String filename, String algorithm) throws Exception {
	      File f = new File(filename);
	      FileInputStream fis = new FileInputStream(f);
	      DataInputStream dis = new DataInputStream(fis);
	      byte[] keyBytes = new byte[(int) f.length()];
	      dis.readFully(keyBytes);
	      dis.close();

	      String temp = new String(keyBytes);
	      String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----\n", "");
	      privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
	      System.out.println("temp key\n"+temp);
	      System.out.println("Private key\n"+privKeyPEM);

//	      Base64 b64 = new Base64();
	      byte [] decoded = Base64.getDecoder().decode(privKeyPEM.replace("\n", ""));
//	      byte [] decoded = b64.decode(privKeyPEM);

	      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
	      KeyFactory kf = KeyFactory.getInstance(algorithm);
	      return kf.generatePrivate(spec);
}