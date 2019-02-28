using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Medidor_Ambiente.Models
{
    public class Coordenadas
    {
        public double Longitud { get; set; }
        public double Latitud { get; set; }
        public double Promedio_Aire { get; set; }
        public double Maximo_Aire { get; set; }
        public double Minimo_Aire { get; set; }
        public double Promedio_Monoxido { get; set; }
        public double Maximo_Monoxido { get; set; }
        public double Minimo_Monoxido { get; set; }
        public double Promedio_Radiacion { get; set; }
        public double Maximo_Radiacion { get; set; }
        public double Minimo_Radiacion { get; set; }
    }
}