import axios, { AxiosError } from "axios";
import {
  all,
  fork,
  put,
  takeEvery,
  takeLatest,
  call,
} from "redux-saga/effects";
import { loginAdminInfoAction } from "../actions/admin/index";
import { T_loginAdminAction, LOGIN_ADMIN_INFO } from "../actions/admin/type";

function* loginAdminFunc(action: T_loginAdminAction) {
  console.log("loginAdminFunc action");
  try {
    if (action.type === "REQUEST_LOGIN_ADMIN_INFO") {
      const { data } = yield call(loginAdminInfoAction.API, action.payload);
      console.log("loginAdminFunc SAGA");
      yield put(loginAdminInfoAction.ACTION.SUCCESS(data));
    }
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.log(error.response?.data);
      yield put(loginAdminInfoAction.ACTION.FAILURE(error.response?.data));
    }
  }
}

function* watchLoginAdminSaga() {
  yield takeLatest(loginAdminInfoAction.ACTION.REQUEST, loginAdminFunc);
}

export default function* adminSaga() {
  yield all([fork(watchLoginAdminSaga)]);
}
