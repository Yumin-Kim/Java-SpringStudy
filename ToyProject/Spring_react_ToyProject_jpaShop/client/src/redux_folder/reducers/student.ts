import { StudentStore } from "../../types/storeType";
import studentSaga from "../sagas/studentSaga";

const stduentInitialState: StudentStore = {};

const studentReducer = (state = studentSaga, action: any) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default studentReducer;
