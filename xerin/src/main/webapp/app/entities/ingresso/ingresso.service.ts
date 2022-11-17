import axios from 'axios';

import { IIngresso } from '@/shared/model/ingresso.model';

const baseApiUrl = 'api/ingressos';

export default class IngressoService {
  public find(id: number): Promise<IIngresso> {
    return new Promise<IIngresso>((resolve, reject) => {
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
}
