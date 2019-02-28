namespace Medidor_Ambiente.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using System.Collections.Generic;
    using System.Data.Entity.Infrastructure;
    using System.Data.SqlClient;

    public partial class Modelo : DbContext
    {
        public Modelo()
            : base("name=Modelo1")
        {
        }

        public virtual DbSet<Pronostico> Pronostico { get; set; }

        public IQueryable ObtenerMonoxidoCarbono()
        {
            var data = from p in Pronostico
                       select new
                       {
                           Monoxido_carbono = p.monoxido_carbono,
                           Fecha = p.fecha,
                           Hora = p.hora,
                           Latitud = p.latitud,
                           Longitud = p.longitud,
                       };
            return data;
        }

        public IQueryable ObtenerAire()
        {
            var data = from p in Pronostico
                       select new
                       {
                           Aire = p.aire,
                           Fecha = p.fecha,
                           Hora = p.hora,
                           Latitud = p.latitud,
                           Longitud = p.longitud
                       };
            return data;
        }

        public IQueryable ObtenerRadiacion()
        {
            var data = from p in Pronostico
                       select new
                       {
                           Radiacion_solar = p.radiacion_solar,
                           Fecha = p.fecha,
                           Hora = p.hora,
                           Latitud = p.latitud,
                           Longitud = p.longitud
                       };
            return data;
        }

        public double ObtenerPromedioMonoxido()
        {
            try
            {
                return Pronostico.Average(p => p.monoxido_carbono);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMaxMonoxido()
        {
            try
            {
                return Pronostico.Max(p => p.monoxido_carbono);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMinMonoxido()
        {
            try
            {
                return Pronostico.Min(p => p.monoxido_carbono);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerPromedioAire()
        {
            try
            {
                return Pronostico.Average(p => p.aire);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMaxAire()
        {
            try
            {
                return Pronostico.Max(p => p.aire);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMinAire()
        {
            try
            {
                return Pronostico.Min(p => p.aire);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerPromedioRadiacion()
        {
            try
            {
                return Pronostico.Average(p => p.radiacion_solar);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMaxRadiacion()
        {
            try
            {
                return Pronostico.Max(p => p.radiacion_solar);
            }
            catch
            {
                return 0;
            }
        }

        public double ObtenerMinRadiacion()
        {
            try
            {
                return Pronostico.Min(p => p.radiacion_solar);
            }
            catch
            {
                return 0;
            }
        }

        public List<Coordenadas> ObtenerLatitudLongitud()
        {
            var data = Database.SqlQuery<Coordenadas>("EXECUTE ObtenerCoordenadas;")
                .ToList();
            return data;
        }

        public List<EstadisticasDiarias> ObtenerEstadDiariasMonoxido()
        {
            var data = this.Database.SqlQuery<EstadisticasDiarias>("EXECUTE ObtenerEstadDiariasMonoxido;")
                .ToList();
            return data;
        }

        public List<EstadisticasDiarias> ObtenerEstadDiariasAire()
        {
            var data = this.Database.SqlQuery<EstadisticasDiarias>("EXECUTE ObtenerEstadDiariasAire;")
                .ToList();
            return data;
        }

        public List<EstadisticasDiarias> ObtenerEstadDiariasRadiacion()
        {
            var data = this.Database.SqlQuery<EstadisticasDiarias>("EXECUTE ObtenerEstadDiariasRadiacion;")
                .ToList();
            return data;
        }

        public List<EstadisticasDiarias> ObtenerEstadDiariasTodos()
        {
            var data = this.Database.SqlQuery<EstadisticasDiarias>("EXECUTE ObtenerEstadDiariasGenerales;")
                .ToList();
            return data;
        }

        public List<DatosHoraUnitario> ObtenerEstadPorHoraAire(DateTime fecha)
        {
            var data = Database.SqlQuery<DatosHoraUnitario>("EXECUTE ObtenerDatosPorHoraAire @fecha;",
                                                             new SqlParameter("@fecha", fecha))
                .ToList();
            return data;
        }

        public List<DatosHoraUnitario> ObtenerEstadPorHoraMonoxido(DateTime fecha)
        {
            var data = Database.SqlQuery<DatosHoraUnitario>("EXECUTE ObtenerDatosPorHoraMonoxido @fecha;",
                                                             new SqlParameter("@fecha", fecha))
                .ToList();
            return data;
        }

        public List<DatosHoraUnitario> ObtenerEstadPorHoraRadiacion(DateTime fecha)
        {
            var data = Database.SqlQuery<DatosHoraUnitario>("EXECUTE ObtenerDatosPorHoraRadiacion @fecha;",
                                                             new SqlParameter("@fecha", fecha))
                .ToList();
            return data;
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Pronostico>()
                .Property(e => e.monoxido_carbono);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.aire);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.radiacion_solar);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.fecha);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.hora);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.latitud);

            modelBuilder.Entity<Pronostico>()
                .Property(e => e.longitud);
        }
    }
}
