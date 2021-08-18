import React from "react";
import { Redirect, Link, BrowserRouter, Switch, Route } from "react-router-dom";
import OriginRemote from "../pages/OriginHome";
import Admin from "../pages/Admin";
import { Layout } from "antd";
import { ContentCustom, HeaderCustom } from "./style";
import Navigation from "./Navigation";
const { Header, Content, Footer } = Layout;

const Basic = () => {
  return (
    <Layout>
      <HeaderCustom>
        <Navigation />
      </HeaderCustom>
      <ContentCustom>
        <Switch>
          <Route path="/" component={OriginRemote} exact={true} />
          <Route path="/admin" component={Admin} />
          <Redirect path="*" to="/" />
        </Switch>
      </ContentCustom>
      <Footer>Footer</Footer>
    </Layout>
  );
};

export default Basic;
