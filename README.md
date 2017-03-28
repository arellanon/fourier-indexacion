# fourier-indexacion
Fourier es un grupo de aplicaciones que emula el funcionamiento de Shazam.
Identifica el contenido de audio que te rodea y explora en la lista de canciones indexadas a su base de datos.

Fourier se encuentra dividido en 3 sub-aplicaciones

* fourier-cliente:
Es el programa encargado de grabar el audio del entorno, codificarlo y enviarlo a fourier-servidor para su reconocimiento.

* fourier-servidor:
Es un servicio que recibe peticiones de identificación de canciones para su resolución.

* fourier-indexacion:
Encargado de agregar nuevas canciones a la base de datos de fourier.
