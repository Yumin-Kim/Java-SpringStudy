import React, { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router";
import { Form, Input, Button, Typography } from "antd";
import {
  basicRoutePathName,
  routeToMappingData,
  IRoutePathNameComponentToEle,
} from "../types/defultType";
import { layout } from "../components/FormComponet";
import { useDispatch, useSelector } from "react-redux";
import { ROOTSTATE } from "../redux_folder/reducers/root";
import {
  loginAdminInfoAction,
  loginAdminAPI,
} from "../redux_folder/actions/admin/index";
const { Title } = Typography;
interface IRouteInfo {
  stubing: typeof basicRoutePathName[number];
}

const MainInputPage = () => {
  const [form] = Form.useForm();
  const { stubing } = useParams<IRouteInfo>();
  const [serilizeData, setSerilizeData] =
    useState<IRoutePathNameComponentToEle | null>(null);
  const dispatch = useDispatch();
  useEffect(() => {
    if (!serilizeData) {
      console.log("MainInputPage useEffect");
      setSerilizeData(
        routeToMappingData.filter(value => value.pathName === stubing)[0]
      );
      console.log(serilizeData);
    }
  }, [stubing]);
  const onFinish = useCallback(
    (values: any) => {
      console.log(values);
      if (stubing === "admin") {
        console.log(stubing);
        console.log(values.name, values.password);
        dispatch(
          loginAdminInfoAction.ACTION.REQUEST({
            name: values.name,
            password: values.password,
          })
        );
      }
    },
    [stubing]
  );
  return (
    <>
      <Title level={1}>{serilizeData?.categoryName}</Title>
      <Title level={2}>{serilizeData?.categoryName}</Title>
      <Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
        {" "}
        {serilizeData?.formTagInInputEl.map((value, index) => (
          <Form.Item
            name={value.name}
            label={value.label}
            rules={[{ required: true }]}
          >
            <Input type={value.inputType} />
          </Form.Item>
        ))}
        <Button type="primary" htmlType="submit">
          가입
        </Button>
      </Form>
    </>
  );
};

export default MainInputPage;
