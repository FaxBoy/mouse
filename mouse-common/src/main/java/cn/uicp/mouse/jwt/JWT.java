package cn.uicp.mouse.jwt;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JWT {

	private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
	
	private static final String EXP = "exp";
	
	private static final String PAYLOAD = "payload";

	/**
	 * get jwt String of object
	 * @param object
	 *            the POJO object
	 * @param maxAge
	 *            the milliseconds of life time
	 * @return the jwt token
	 */
	public static <T> String sign(T object, long maxAge) {
		try {
			final JWTSigner signer = new JWTSigner(SECRET);
			final Map<String, Object> claims = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(object);
			claims.put(PAYLOAD, jsonString);
			System.out.println(System.currentTimeMillis() + maxAge);
			System.out.println(System.currentTimeMillis() + 10000);
			claims.put(EXP, System.currentTimeMillis() + 20000);
			//claims.put(EXP, 10000);
			return signer.sign(claims);
		} catch(Exception e) {
			return null;
		}
	}
	
	
	/**
	 * get the object of jwt if not expired
	 * @param jwt
	 * @return POJO object
	 */
	public static<T> T unsign(String jwt, Class<T> classT) {
		final JWTVerifier verifier = new JWTVerifier(SECRET);
	    try {
			final Map<String,Object> claims= verifier.verify(jwt);
			if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
				long exp = (Long)claims.get(EXP);
				long currentTimeMillis = System.currentTimeMillis();
				if (exp > currentTimeMillis) {
					String json = (String)claims.get(PAYLOAD);
					ObjectMapper objectMapper = new ObjectMapper();
					return objectMapper.readValue(json, classT);
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
