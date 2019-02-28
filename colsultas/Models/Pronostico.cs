namespace Medidor_Ambiente.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Pronostico")]
    public partial class Pronostico
    {
        public int id { get; set; }

        public double monoxido_carbono { get; set; }

        public double aire { get; set; }

        public double radiacion_solar { get; set; }

        public DateTime fecha { get; set; }

        public TimeSpan hora { get; set; }

        public double latitud { get; set; }

        public double longitud { get; set; }
    }
}
