using System.Web.Http;

namespace Medidor_Ambiente
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Configuración y servicios de API web

            // Rutas de API web
            config.MapHttpAttributeRoutes();            

            config.Routes.MapHttpRoute(
                name: "Monoxido_Carbono",
                routeTemplate: "api/MonoxidoCarbono",
                defaults: new { controller = "Estadistica", action = "GetMonoxido" }
            );

            config.Routes.MapHttpRoute(
                name: "Aire",
                routeTemplate: "api/Aire",
                defaults: new { controller = "Estadistica", action = "GetAire" }
            );

            config.Routes.MapHttpRoute(
                name: "Radiacion",
                routeTemplate: "api/Radiacion",
                defaults: new { controller = "Estadistica", action = "GetRadiacion" }
            );

            config.Routes.MapHttpRoute(
                name: "Estadisticas",
                routeTemplate: "api/Estadisticas",
                defaults: new { controller = "Estadistica", action = "GetEstadisticos" }
            );

            config.Routes.MapHttpRoute(
                name: "GPS",
                routeTemplate: "api/Coordenadas",
                defaults: new { controller = "Estadistica", action = "GetLocLat" }
            );

            config.Routes.MapHttpRoute(
                name: "Monoxido_Datos",
                routeTemplate: "api/MonoxidoCarbono/Datos",
                defaults: new { controller = "Estadistica", action = "GetPromedioDiarioMonoxido" }
            );

            config.Routes.MapHttpRoute(
                name: "Aire_Datos",
                routeTemplate: "api/Aire/Datos",
                defaults: new { controller = "Estadistica", action = "GetPromedioDiarioAire" }
            );

            config.Routes.MapHttpRoute(
                name: "Radiacion_Datos",
                routeTemplate: "api/Radiacion/Datos",
                defaults: new { controller = "Estadistica", action = "GetPromedioDiarioRadiacion" }
            );



            config.Routes.MapHttpRoute(
                name: "Monoxido_DatosPorDia",
                routeTemplate: "api/MonoxidoCarbono/PorDia",
                defaults: new { controller = "Estadistica", action = "GetEstadPorHoraMonoxido" }
            );

            config.Routes.MapHttpRoute(
                name: "Aire_DatosPorDia",
                routeTemplate: "api/Aire/PorDia",
                defaults: new { controller = "Estadistica", action = "GetEstadPorHoraAire" }
            );

            config.Routes.MapHttpRoute(
                name: "Radiacion_PorDia",
                routeTemplate: "api/Radiacion/PorDia",
                defaults: new { controller = "Estadistica", action = "GetEstadPorHoraRadiacion" }
            );




            config.Routes.MapHttpRoute(
                name: "Estadisticas_Diarias",
                routeTemplate: "api/Estadisticas/Diarias",
                defaults: new { controller = "Estadistica", action = "GetPromedioDiarioTodos" }
            );

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

        }
    }
}
