import axios from 'axios';
import { ConfirmacaoContext } from './confirmacao.model';

const baseApiUrl = 'api/processo-ingresso/confirmacao';

export default class ConfirmacaoService {
  public loadContext(taskId: number): Promise<ConfirmacaoContext> {
    return new Promise<ConfirmacaoContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<ConfirmacaoContext> {
    return new Promise<ConfirmacaoContext>((resolve, reject) => {
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

  public complete(confirmacaoContext: ConfirmacaoContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, confirmacaoContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
