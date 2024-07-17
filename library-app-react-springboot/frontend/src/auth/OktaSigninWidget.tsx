import OktaSignIn from "@okta/okta-signin-widget";
import "@okta/okta-signin-widget/dist/css/okta-sign-in.min.css";
import { useEffect, useRef } from "react";
import { OktaConfig } from "../lib/OktaConfig";

interface OktaSignInWidgetProps {
  onSuccess: (tokens: any) => void;
  onError: (err: Error) => void;
}

const OktaSignInWidget = ({ onSuccess, onError }: OktaSignInWidgetProps) => {
  const widgetRef = useRef(null);

  useEffect(() => {
    if (!widgetRef.current) {
      return;
    }

    const widget = new OktaSignIn(OktaConfig);
    widget.showSignInToGetTokens({
      el: widgetRef.current,
    }).then(onSuccess).catch(onError);

    return () => {
      widget.remove();
    };
  }, [onSuccess, onError]);

  return (
    <div className="container m-5 mb-5">
      <div ref={widgetRef}></div>
    </div>
  );
};

export default OktaSignInWidget;
