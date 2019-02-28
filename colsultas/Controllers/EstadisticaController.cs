using Medidor_Ambiente.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Medidor_Ambiente.Controllers
{
    public class EstadisticaController : ApiController
    {
        private Modelo db = new Modelo();

        public List<Estadistico> GetEstadisticos()
        {
            List<Estadistico> lista = new List<Estadistico>
            {
                new Estadistico(
                "Monoxido",
                db.ObtenerPromedioMonoxido(),
                db.ObtenerMaxMonoxido(),
                db.ObtenerMinMonoxido()
            ),

                new Estadistico(
                "Aire",
                db.ObtenerPromedioAire(),
                db.ObtenerMaxAire(),
                db.ObtenerMinAire()
            ),

                new Estadistico(
                "Radiacion",
                db.ObtenerPromedioRadiacion(),
                db.ObtenerMaxRadiacion(),
                db.ObtenerMinRadiacion()
            )
            };

            return lista;
        }

        public IQueryable GetMonoxido()
        {
            return db.ObtenerMonoxidoCarbono();
        }

        public IQueryable GetAire()
        {
            return db.ObtenerAire();
        }

        public IQueryable GetRadiacion()
        {
            return db.ObtenerRadiacion();
        }

        public List<Coordenadas> GetLocLat()
        {
            return db.ObtenerLatitudLongitud();
        }

        public List<EstadisticasDiarias> GetPromedioDiarioMonoxido()
        {
            return db.ObtenerEstadDiariasMonoxido();
        }

        public List<EstadisticasDiarias> GetPromedioDiarioAire()
        {
            return db.ObtenerEstadDiariasAire();
        }

        public List<EstadisticasDiarias> GetPromedioDiarioRadiacion()
        {
            return db.ObtenerEstadDiariasRadiacion();
        }

        public List<EstadisticasDiarias> GetPromedioDiarioTodos()
        {
            return db.ObtenerEstadDiariasTodos();
        }

        public List<DatosHoraUnitario> GetEstadPorHoraAire(DateTime fecha)
        {
            return db.ObtenerEstadPorHoraAire(fecha);
        }

        public List<DatosHoraUnitario> GetEstadPorHoraMonoxido(DateTime fecha)
        {
            return db.ObtenerEstadPorHoraMonoxido(fecha);
        }

        public List<DatosHoraUnitario> GetEstadPorHoraRadiacion(DateTime fecha)
        {
            return db.ObtenerEstadPorHoraRadiacion(fecha);
        }
    }
}
