using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Medidor_Ambiente.Models
{
    public class EstadisticasDiarias
    {
        public string Sensor { get; set; }
        public DateTime Fecha { get; set;}
        public double Promedio { get; set; }
        public double Maximo { get; set; }
        public double Minimo { get; set; }
    }
}