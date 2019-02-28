DROP PROCEDURE ObtenerEstadDiariasMonoxido;
GO
CREATE PROCEDURE ObtenerEstadDiariasMonoxido
AS   
	SELECT 'Monoxido_Carbono' as Sensor, P.fecha as Fecha,
	AVG(P.monoxido_carbono) as Promedio,
	MAX(P.monoxido_carbono) Maximo, MIN(P.monoxido_carbono) Minimo FROM Pronostico P
	GROUP BY Fecha;
GO

CREATE PROCEDURE ObtenerEstadDiariasAire
AS   
	SELECT 'Aire' as Sensor, P.fecha as Fecha,
	AVG(P.aire) as Promedio,
	MAX(P.aire) Maximo, MIN(P.aire) Minimo FROM Pronostico P
	GROUP BY Fecha;
GO


CREATE PROCEDURE ObtenerEstadDiariasRadiacion
AS   
	SELECT 'Radiacion' as Sensor, P.fecha as Fecha,
	AVG(P.radiacion_solar) as Promedio,
	MAX(P.radiacion_solar) Maximo, MIN(P.radiacion_solar) Minimo FROM Pronostico P
	GROUP BY Fecha;
GO

DROP PROCEDURE ObtenerEstadDiariasGenerales;
GO
CREATE PROCEDURE ObtenerEstadDiariasGenerales
AS
	SELECT 'Monoxido_Carbono' as Sensor, P.fecha as Fecha,
	AVG(P.monoxido_carbono) as Promedio,
	MAX(P.monoxido_carbono) Maximo, MIN(P.monoxido_carbono) Minimo FROM Pronostico P
	GROUP BY Fecha
	UNION
	SELECT 'Aire' as Sensor, P.fecha as Fecha,
	AVG(P.aire) as Promedio,
	MAX(P.aire) Maximo, MIN(P.aire) Minimo FROM Pronostico P
	GROUP BY Fecha
	UNION
	SELECT 'Radiacion' as Sensor, P.fecha as Fecha,
	AVG(P.radiacion_solar) as Promedio,
	MAX(P.radiacion_solar) Maximo, MIN(P.radiacion_solar) Minimo FROM Pronostico P
	GROUP BY Fecha;
GO

EXECUTE ObtenerEstadDiariasGenerales;


Drop Procedure ObtenerCoordenadas;
GO
CREATE PROCEDURE ObtenerCoordenadas
AS   
	SELECT distinct P.longitud as Longitud, P.latitud as Latitud, 
		AVG(P.aire) as Promedio_Aire, 
			MAX(P.aire) as Maximo_Aire,
				MIN(P.aire) as Minimo_Aire,
		AVG(P.monoxido_carbono) as Promedio_Monoxido,
			MAX(P.monoxido_carbono) Maximo_Monoxido,
				MIN(P.monoxido_carbono) Minimo_Monoxido,
		AVG(P.radiacion_solar) as Promedio_Radiacion,
			MAX(P.radiacion_solar) Maximo_Radiacion,
				MIN(P.radiacion_solar) Minimo_Radiacion
	FROM Pronostico as P
	GROUP BY Longitud, Latitud;
GO
EXECUTE ObtenerCoordenadas;


Drop Procedure ObtenerDatosPorHora;
GO
CREATE PROCEDURE ObtenerDatosPorHora
    @Fecha date
AS
	SET NOCOUNT ON;
	SELECT Cast(dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0) as time )as Hora,
		AVG(P.aire) as Promedio_Aire, 
			MAX(P.aire) as Maximo_Aire,
				MIN(P.aire) as Minimo_Aire,
		AVG(P.monoxido_carbono) as Promedio_Monoxido,
			MAX(P.monoxido_carbono) Maximo_Monoxido,
				MIN(P.monoxido_carbono) Minimo_Monoxido,
		AVG(P.radiacion_solar) as Promedio_Radiacion,
			MAX(P.radiacion_solar) Maximo_Radiacion,
				MIN(P.radiacion_solar) Maximo_Radiacion
	FROM Pronostico as P
	Where P.fecha = @Fecha
	GROUP BY dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0)
	ORDER BY Hora;
GO

EXECUTE ObtenerDatosPorHora '2019-02-25';

GO
CREATE PROCEDURE ObtenerDatosPorHoraAire
    @Fecha date
AS
	SET NOCOUNT ON;
	SELECT 'AIRE' as Sensor,
	Cast(dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0) as time )as Hora,
		AVG(P.aire) as Promedio, 
			MAX(P.aire) as Maximo,
				MIN(P.aire) as Minimo
	FROM Pronostico as P
	Where P.fecha = @Fecha
	GROUP BY dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0)
	ORDER BY Hora;
GO
EXECUTE ObtenerDatosPorHoraAire '2019-02-23';
GO
CREATE PROCEDURE ObtenerDatosPorHoraMonoxido
    @Fecha date
AS
	SET NOCOUNT ON;
	SELECT 'MONOXIDO' as Sensor,
	Cast(dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0) as time )as Hora,
		AVG(P.monoxido_carbono) as Promedio,
			MAX(P.monoxido_carbono) Maximo,
				MIN(P.monoxido_carbono) Minimo
	FROM Pronostico as P
	Where P.fecha = @Fecha
	GROUP BY dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0)
	ORDER BY Hora;
GO
EXECUTE ObtenerDatosPorHoraMonoxido '2019-02-23';
GO
CREATE PROCEDURE ObtenerDatosPorHoraRadiacion
    @Fecha date
AS
	SET NOCOUNT ON;
	SELECT 'RADIACION' as Sensor,
	Cast(dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0) as time )as Hora,
		AVG(P.radiacion_solar) as Promedio,
			MAX(P.radiacion_solar) Maximo,
				MIN(P.radiacion_solar) Maximo
	FROM Pronostico as P
	Where P.fecha = @Fecha
	GROUP BY dateadd(minute, datediff(minute, 0, P.hora) / 10 * 10, 0)
	ORDER BY Hora;
GO
EXECUTE ObtenerDatosPorHoraRadiacion '2019-02-23';