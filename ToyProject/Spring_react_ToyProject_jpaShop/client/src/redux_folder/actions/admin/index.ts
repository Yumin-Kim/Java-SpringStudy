import axios from "axios";
import { createActionAxiosGetVerionToAPIPARMA } from "../../../types/action";
import { I_DefaultAdmin_Admin } from "../../../types/storeType";
import { LOGIN_ADMIN_INFO, CREATE_ADMIN_INFO } from "./type";

axios.defaults.baseURL = "http://localhost:5050/api";

export interface I_AxiosDefaultDataFormat<D> {
  data: D;
  message: string;
}

export const loginAdminAPI = async (
  adminDefault: Pick<I_DefaultAdmin_Admin, "name" | "password">
): Promise<
  I_AxiosDefaultDataFormat<Omit<I_DefaultAdmin_Admin, "password">>
> => {
  return await axios.post(
    `/admin/rootinfo/login?name=${adminDefault.name}&password=${adminDefault.password}`
  );
};

export const loginAdminInfoAction = createActionAxiosGetVerionToAPIPARMA(
  LOGIN_ADMIN_INFO,
  loginAdminAPI
);

export const createAdminAPI = async (
  adminInfo: I_DefaultAdmin_Admin
): Promise<
  I_AxiosDefaultDataFormat<Omit<I_DefaultAdmin_Admin, "password">>
> => {
  return await axios.post("/admin/rootinfo/", adminInfo);
};

export const createAdminInfoAction = createActionAxiosGetVerionToAPIPARMA(
  CREATE_ADMIN_INFO,
  createAdminAPI
);
