import { all, fork, takeLatest } from "@redux-saga/core/effects";

function* getHeloSaga(action: any) {}

function* watchHell() {
  // yield takeLatest("REQUEST",getHeloSaga())
}

export default function* adminSaga() {
  yield all([fork(watchHell)]);
}
