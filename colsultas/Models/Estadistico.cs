using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Medidor_Ambiente.Models
{
    public class Estadistico
    {
        public Estadistico(string sensor, double promedio,
            double maximo, double minimo)
        {
            Sensor = sensor;
            Promedio = promedio;
            Maximo = maximo;
            Minimo = minimo;
        }

        public string Sensor { get; set; }
        public double Promedio { get; set; }
        public double Maximo { get; set; }
        public double Minimo { get; set; }
    }
}