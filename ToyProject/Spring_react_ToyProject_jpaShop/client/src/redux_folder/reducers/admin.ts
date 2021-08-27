import { AdminStore } from "../../types/storeType";

const adminIniitialState: AdminStore = {};

const adminReducer = (state = adminIniitialState, action: any) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default adminReducer;
