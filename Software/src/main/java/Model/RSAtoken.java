/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author User
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author droro
 */
public class RSAtoken {

    public static String crearToken(Sesion x) {
        try {
            String xc = "parangaricutimiricuaro";
            byte[] c = Base64.getEncoder().encode(xc.getBytes());
            String zx = new String(c);
            Algorithm algorithm = Algorithm.HMAC256("secret");

            String token = JWT.create()
                    .withIssuer("yourHome")
                    .withClaim("idUser", x.getIdUsuario())
                    .withClaim("email", x.getCorreo())
                    .withClaim("typeUser", x.getTipoUsuario())
                    .withClaim("Authorized.usap", zx)
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException | UnsupportedEncodingException | IllegalArgumentException exception) {
            return null;
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    public static DecodedJWT verificarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("yourHome")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim auth = jwt.getClaim("Authorized.usap");
            String cv = auth.asString();
            byte[] lst = Base64.getDecoder().decode(cv.getBytes());
            String result = new String(lst);
            if ("parangaricutimiricuaro".equals(result)) {
                return jwt;
            } else {
                return null;
            }

        } catch (JWTVerificationException | UnsupportedEncodingException | IllegalArgumentException ex) {
            System.out.println("ERROR EN VERIFICAR TOKEN" + ex.getMessage());
            return null;
        }
    }

    public static Sesion absToken(DecodedJWT x) {
        try {
            Sesion aux = new Sesion();
            Map<String, Claim> listCLA = x.getClaims();
            aux.setIdUsuario(listCLA.get("idUser").asString());
            aux.setCorreo(listCLA.get("email").asString());
            aux.setTipoUsuario(listCLA.get("typeUser").asString());
            return aux;
        } catch (Exception e) {
            System.out.println("ERROR EN ABSTOKEN " + e.getMessage());
            return null;
        }
    }

    public static Sesion obtenerSesionToken(HttpServletRequest req) {

        try {

            Cookie[] lisC = req.getCookies();

            if (lisC != null && lisC.length > 0) {
                for (Cookie cookie : lisC) {
                    if ("TK.Yourhome".equals(cookie.getName())) {

                        DecodedJWT tok = RSAtoken.verificarToken(cookie.getValue());
                        if (tok != null) {

                            Sesion ses = RSAtoken.absToken(tok);

                            if (ses != null) {
                                return ses;
                            } else {
                                return null;
                            }

                        } else {
                            return null;
                        }

                    }
                }

            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR EN EL RSAtoken sesion: " + e.getMessage());
            return null;
        }
    }

    public static HttpServletResponse limpiarCookieToken(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] lisC = req.getCookies();
        if (lisC != null && lisC.length > 0) {
            for (Cookie cookie : lisC) {
                if ("TK.Yourhome".equals(cookie.getName())) {

                    cookie.setValue("");
                    res.addCookie(cookie);

                }
            }

        }

        return res;

    }

}
