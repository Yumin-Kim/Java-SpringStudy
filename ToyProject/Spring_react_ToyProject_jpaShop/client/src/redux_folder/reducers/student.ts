import { StudentStore } from "../../types/storeType";
import studentSaga from "../sagas/studentSaga";

const stduentInitialState: StudentStore = {
  studentInfo: null,
  requestStudentInfo: null,
  getDefaultStudentInfo: null,
  createStudent: null,
  modifyStudentInfo: null,
  integrationErrorMessage: null,
  integrationRequestMessage: null,
  integrationSucessMessage: null,
};

const studentReducer = (state = stduentInitialState, action: any) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default studentReducer;
