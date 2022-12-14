/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import IngressoService from '@/entities/ingresso/ingresso.service';
import { Ingresso } from '@/shared/model/ingresso.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Ingresso Service', () => {
    let service: IngressoService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new IngressoService();
      currentDate = new Date();
      elemDefault = new Ingresso(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            data: dayjs(currentDate).format(DATE_FORMAT),
            nascimentoComprador: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Ingresso', async () => {
        const returnedFromService = Object.assign(
          {
            horarioJogo: 'BBBBBB',
            timeMandante: 'BBBBBB',
            timeVisitante: 'BBBBBB',
            data: dayjs(currentDate).format(DATE_FORMAT),
            setorEstadio: 'BBBBBB',
            assentoEstadio: 'BBBBBB',
            nomeComprador: 'BBBBBB',
            cpfComprador: 'BBBBBB',
            nascimentoComprador: dayjs(currentDate).format(DATE_FORMAT),
            numeroCartao: 1,
            validadeCartao: 'BBBBBB',
            codigoCartao: 1,
            emailComprador: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            data: currentDate,
            nascimentoComprador: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Ingresso', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
