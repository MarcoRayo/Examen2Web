<script>
import axios from 'axios';

export default {
  data() {
    return {
      libros: null,
      libro: {
        "nombre": "",
        "autor": "",
        "fecha": ""
      }
    }
    
  },
  methods: {
    created() {
      this.buscar = null;
      axios
        .get("http://localhost:8080/api/libros")
        .then((response) => {
          this.libros = response.data;
          console.log(libros)
        })
        .catch((error) => {
          console.log("There was an error: " + error);
        });
    },
  },
  mounted(){
    this.created()
  }
    
  
}
</script>

<template>
  <div class="container row mx-auto mt-4">
    <transition-group name="zoom" tag="div" class="d-flex flex-row flex-wrap">
      <div class="col-md-4" v-for="libro in libros" :key="libro.id" style="padding-left: 1rem;">
        <b-card :title="libro.nombreLibro" img-src="https://picsum.photos/600/300/?image=25" img-alt="Image" img-top tag="article"
          style="max-width: 20rem; max-height: 30rem;" class="mb-2 mt-2 text-center">
          <b-card-text>
            <h6>Autor: {{ libro.nombreAutor }}</h6>
            Fecha: {{ libro.fechaPublicacion }}
          </b-card-text>
        </b-card>
      </div>
    </transition-group>
  </div>
</template>

<style scoped>
</style>
