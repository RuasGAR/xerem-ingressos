import axios from 'axios';
import { EscolherSetorContext } from './escolher-setor.model';

const baseApiUrl = 'api/processo-ingresso/escolher-setor';

export default class EscolherSetorService {
  public loadContext(taskId: number): Promise<EscolherSetorContext> {
    return new Promise<EscolherSetorContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<EscolherSetorContext> {
    return new Promise<EscolherSetorContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(escolherSetorContext: EscolherSetorContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, escolherSetorContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
