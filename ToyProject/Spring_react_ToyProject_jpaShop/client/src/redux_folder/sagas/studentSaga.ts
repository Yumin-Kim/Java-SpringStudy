import { all, fork, takeLatest } from "@redux-saga/core/effects";

function* getHeloSaga(action: any) {}

function* watchSaga() {
  // yield takeLatest("REQUEST",getHeloSaga())
}

export default function* studentSaga() {
  yield all([fork(watchSaga)]);
}
