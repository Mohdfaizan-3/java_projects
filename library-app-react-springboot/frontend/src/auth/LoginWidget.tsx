import { useOktaAuth } from "@okta/okta-react";
import { Navigate } from "react-router-dom";
import OktaSignInWidget from "./OktaSigninWidget";


interface LoginWidgetProps {
    config: Record<string, any>; // Define a more specific type based on your config if possible
  }


 const LoginWidget = ({ config }: LoginWidgetProps) => {
  const { oktaAuth, authState } = useOktaAuth();

  const onSuccess = (token: any) => {
    oktaAuth.handleLoginRedirect(token);
  };
  

  const onError = (error: any) => {
    console.log(error);
  };

  if (!authState) {
    return <div>loading</div>;
  }

  return authState.isAuthenticated ? (
    <Navigate to={{ pathname: "/" }} />
  ) : (
    <OktaSignInWidget onSuccess={onSuccess} onError={onError} />
  );
};

export default LoginWidget;