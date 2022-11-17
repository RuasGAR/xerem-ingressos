import axios from 'axios';

import { IProcessoIngresso } from '@/shared/model/processo-ingresso.model';

const baseApiUrl = 'api/processo-ingressos';

export default class ProcessoIngressoService {
  public find(id: number): Promise<IProcessoIngresso> {
    return new Promise<IProcessoIngresso>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IProcessoIngresso): Promise<IProcessoIngresso> {
    return new Promise<IProcessoIngresso>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
