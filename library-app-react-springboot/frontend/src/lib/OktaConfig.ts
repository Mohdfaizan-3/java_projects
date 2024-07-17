
export const OktaConfig = {
    clientId : '0oaia400psVTd1LeG5d7',
    issuer : "https://dev-64264741.okta.com/oauth2/default",
    redirectUri : 'http://localhost:3000/login/callback',
    // redirectUri: window.location.origin + "/login/callback",
    scopes: ["openid", "profile", "email"],
    pkce: true,
    disableHttpsCheck : true
}