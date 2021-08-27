import React from "react";
import { Button } from "antd";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <>
      <h1>Hello Home</h1>
      <div
        style={{
          margin: "0 auto",
          textAlign: "center",
        }}
      >
        <Button block>
          <Link to="/make">계정 생성</Link>
        </Button>
        <Button block>
          <Link to="/find">계정 찾기</Link>
        </Button>
        <Button block>
          <Link to="/modify">계정 조회 및 수정</Link>
        </Button>
        <Button block>
          <Link to="/admin">관리자 페이지</Link>
        </Button>
      </div>
    </>
  );
};

export default Home;
