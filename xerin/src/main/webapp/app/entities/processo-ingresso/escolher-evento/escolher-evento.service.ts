import axios from 'axios';
import { EscolherEventoContext } from './escolher-evento.model';

const baseApiUrl = 'api/processo-ingresso/escolher-evento';

export default class EscolherEventoService {
  public loadContext(taskId: number): Promise<EscolherEventoContext> {
    return new Promise<EscolherEventoContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<EscolherEventoContext> {
    return new Promise<EscolherEventoContext>((resolve, reject) => {
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

  public complete(escolherEventoContext: EscolherEventoContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, escolherEventoContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
