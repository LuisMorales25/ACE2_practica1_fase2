using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Http;
using System.Web.Http.Description;
using Medidor_Ambiente.Models;

namespace Medidor_Ambiente.Controllers
{
    public class PronosticosController : ApiController
    {
        private Modelo db = new Modelo();

        // GET: api/Pronosticos
        public IQueryable<Pronostico> GetPronostico()
        {
            return db.Pronostico;
        }

        // GET: api/Pronosticos/5
        [ResponseType(typeof(Pronostico))]
        public IHttpActionResult GetPronostico(int id)
        {
            Pronostico pronostico = db.Pronostico.Find(id);
            if (pronostico == null)
            {
                return NotFound();
            }

            return Ok(pronostico);
        }

        // PUT: api/Pronosticos/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPronostico(int id, Pronostico pronostico)
        {
            if (pronostico == null)
            {
                return NotFound();
            }

            if (pronostico.fecha == null)
            {
                pronostico.fecha = System.DateTime.Now;
            }
            else
            {
                if (pronostico.fecha.Equals(""))
                {
                    pronostico.fecha = System.DateTime.Now;
                }
            }

            if (pronostico.hora == null)
            {
                pronostico.hora = System.DateTime.Now.TimeOfDay;
            }
            else
            {
                if (pronostico.hora.Equals(""))
                {
                    pronostico.hora = System.DateTime.Now.TimeOfDay;
                }
            }

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pronostico.id)
            {
                return BadRequest();
            }

            db.Entry(pronostico).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PronosticoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        [ResponseType(typeof(Pronostico))]
        public IHttpActionResult GetPronostico(float monoxido_carbono, float aire,
            float radiacion_solar, float latitud, float longitud)
        {
            Pronostico pronostico = new Pronostico
            {
                aire = aire,
                fecha = System.DateTime.Now,
                hora = System.DateTime.Now.TimeOfDay,
                latitud = latitud,
                longitud = longitud,
                monoxido_carbono = monoxido_carbono,
                radiacion_solar = radiacion_solar
            };

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Pronostico.Add(pronostico);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = pronostico.id }, pronostico);            
        }

        // POST: api/Pronosticos
        [ResponseType(typeof(Pronostico))]
        public IHttpActionResult PostPronostico(Pronostico pronostico)
        {
            if (pronostico == null)
            {
                return NotFound();
            }

            if (pronostico.fecha == null)
            {
                pronostico.fecha = System.DateTime.Now;
            }
            else
            {
                if (pronostico.fecha.Equals("")) {
                    pronostico.fecha = System.DateTime.Now;
                }
            }

            if (pronostico.hora == null)
            {
                pronostico.hora = System.DateTime.Now.TimeOfDay;
            }
            else
            {
                if (pronostico.hora.Equals(""))
                {
                    pronostico.hora = System.DateTime.Now.TimeOfDay;
                }
            }

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Pronostico.Add(pronostico);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = pronostico.id }, pronostico);
        }

        // DELETE: api/Pronosticos/5
        [ResponseType(typeof(Pronostico))]
        public IHttpActionResult DeletePronostico(int id)
        {
            Pronostico pronostico = db.Pronostico.Find(id);
            if (pronostico == null)
            {
                return NotFound();
            }

            db.Pronostico.Remove(pronostico);
            db.SaveChanges();

            return Ok(pronostico);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PronosticoExists(int id)
        {
            return db.Pronostico.Count(e => e.id == id) > 0;
        }
    }
}