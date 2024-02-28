<template>
    <div>
        <div id="carousel">
            <carouselPelis v-show="showElement"></carouselPelis>
        </div>
        <div class="row">
            <div id="btn" class="col-md-8">
                <div class="row" style="padding-top: 1rem;">
                    <div class="col-md-2 mx-auto">
                        <b-button variant="outline-info" size="sm" @click="searhAutor()">Ordenar por autor</b-button>
                    </div>
                    <div class="col-md-2 mx-auto">
                        <b-button variant="outline-info" size="sm" @click="searhFecha()">Ordenar por fecha</b-button>
                    </div>
                    <div class="col-md-2 mx-auto">
                        <b-button variant="outline-info" size="sm">Ordenar por imagen</b-button>
                    </div>
                </div>
                <div class="" >
                    <div class="row">


                        <div class="col-md-12">
                            <div class="container row mx-auto mt-4">
                                <transition-group name="zoom" tag="div" class="d-flex flex-row flex-wrap">
                                    <div class="col-md-4" v-for="libro in libros" :key="libro.id"
                                        style="padding-left: 1rem;">
                                        <b-card :title="libro.nombreLibro" img-src="https://picsum.photos/600/300/?image=25"
                                            img-alt="Image" img-top tag="article"
                                            style="max-width: 20rem; max-height: 30rem;" class="mb-2 mt-2 text-center">
                                            <b-card-text>
                                                <h6>Autor: {{ libro.nombreAutor }}</h6>
                                                Fecha: {{ libro.fechaPublicacion }}
                                            </b-card-text>
                                            <div class="row">
                                                <div class="col">
                                                    <b-button variant="outline-danger" size="sm" @click="onDelete(libro.id)">Eliminar</b-button>
                                                </div>    
                                                <div class="col">
                                                    <b-button v-b-modal.modal-2 variant="outline-warning" size="sm" @click="edit
                                                    (libro.nombreLibro,libro.nombreAutor, libro.fechaPublicacion,libro.id)">Editar</b-button>
                                                </div>                                                                                            
                                            </div>                                                                                    
                                        </b-card>
                                    </div>
                                </transition-group>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="drag" class="col-md-4 mx-auto" style="padding: 1rem;">
                <div>
                    <div class="row">
                        <div class="col mx-auto text-center">
                            <b-button v-b-modal.modal-1 class="btn btn-success float-right" size="sm">Agregar
                                Nueva</b-button>
                            <b-modal id="modal-1" title="Agregar Pelicula" hide-footer="true">
                                <b-form @submit="onSubmit" @reset="onReset">

                                    <b-form-group id="input-group-name" label="Nombre:" label-for="input-name">
                                        <b-form-input id="input-name" v-model="libro.nombre" type="text"
                                            required></b-form-input>
                                    </b-form-group>

                                    <b-form-group id="input-group-autor" label="Autor:" label-for="input-autor">
                                        <b-form-input id="input-autor" v-model="libro.autor" required></b-form-input>
                                    </b-form-group>

                                    <b-form-group id="input-group-fecha" label="Fecha de publicación:"
                                        label-for="input-fecha">
                                        <b-form-input id="input-fecha" v-model="libro.fecha" type="date"
                                            required></b-form-input>
                                    </b-form-group>

                                    <div class="col-5 mx-auto container mt-3">
                                        <b-button type="submit" variant="success">Aceptar</b-button>
                                        <b-button type="reset" variant="danger"
                                            style="margin-left: 0.5rem;">Borrar</b-button>
                                    </div>                                    
                                </b-form>
                            </b-modal>
                            <b-modal id="modal-2" title="Editar Pelicula" hide-footer="true">
                                <b-form @submit="onEdit">

                                    <b-form-group id="input-group-name" label="Nombre:" label-for="input-name">
                                        <b-form-input id="input-name" v-model="libro.nombre" type="text"
                                            required></b-form-input>
                                    </b-form-group>

                                    <b-form-group id="input-group-autor" label="Autor:" label-for="input-autor">
                                        <b-form-input id="input-autor" v-model="libro.autor" required></b-form-input>
                                    </b-form-group>

                                    <b-form-group id="input-group-fecha" label="Fecha de publicación:"
                                        label-for="input-fecha">
                                        <b-form-input id="input-fecha" v-model="libro.fecha" type="date"
                                            required></b-form-input>
                                    </b-form-group>

                                    <div class="mx-auto container mt-3">
                                        <b-button type="submit" variant="success">Aceptar</b-button>

                                    </div>                                    
                                </b-form>
                            </b-modal>
                            <h1>drag</h1>
                            <div class="draggable-item" style="height: 120px;">
                                Arrastra para editar
                            </div>
                            <div class="draggable-item" style="height: 120px;">
                                Arrastra para eliminar
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import carouselPelis from "../src/components/carouselpelis.vue"

export default {
    data() {
        return {
            items: ['Item 1', 'Item 2', 'Item 3'],
            newItem: '',
            dragIndex: null,
            libro: {
                "nombre": "",
                "fecha": "",
                "autor": ""
            },
            libros: null,
            showElement: true,
            lastScrollPosition: 0,
        }
    },
    mounted() {
        this.created()
        window.addEventListener("scroll", this.onScroll);
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
        edit(name,autor,fecha,id){
            this.libro.nombre = name;
            this.libro.autor = autor;
            this.libro.fecha = fecha;                        
        },
        onEdit(){
            const formData = new FormData();
            formData.append("nombre", this.libro.nombre);
            formData.append("autor", this.libro.autor);
            formData.append("fecha", this.libro.fecha);
            axios
                .get(`http://localhost:8080/api/libros/?id=${id}`, formData)
                .then((response) => {
                    console.log(response)
                })
                .catch((error) => {
                    console.log("There was an error: " + error);
                });
        },
        onDelete(id){
            axios
                .delete(`http://localhost:8080/api/libros/${id}`)
                .then((response) => {
                    console.log(response)
                })
                .catch((error) => {
                    console.log("There was an error: " + error);
                });
                
            this.created()
        },
        addItem() {
            if (this.newItem.trim() !== '') {
                this.items.push(this.newItem.trim());
                this.newItem = '';
            }
        },
        removeItem(index) {
            this.items.splice(index, 1);
        },
        onDragStart(index) {
            this.dragIndex = index;
        },
        onDragOver(index) {
            event.preventDefault();
        },
        onDrop(index) {
            if (this.dragIndex !== null) {
                const draggedItem = this.items[this.dragIndex];
                this.items.splice(this.dragIndex, 1);
                this.items.splice(index, 0, draggedItem);
                this.dragIndex = null;
            }
        },
        onDragEnd() {
            this.dragIndex = null;
        },
        onReset(e) {
            e.preventDefault();
            this.libro.nombre = '';
            this.libro.autor = '';
            this.libro.fecha = '';
        },
        onSubmit(event) {
            const formData = new FormData();
            formData.append("nombre", this.libro.nombre);
            formData.append("autor", this.libro.autor);
            formData.append("fecha", this.libro.fecha);
            console.log(formData);
            axios.post('http://localhost:8080/api/libros', formData, {

            })
                .then(response => {
                    mounted();
                })
                .catch(error => {
                    console.error('Error en el registro:', error);
                });
        },
        searhAutor() {
            axios
                .get("http://localhost:8080/api/libros/buscarLibroPorAutor")
                .then((response) => {
                    console.log(response.data);
                    this.libros = response.data;
                })
                .catch((error) => {
                    console.log("There was an error: " + error);
                });
        },
        searhFecha(){
            axios
                .get("http://localhost:8080/api/libros/buscarLibroPorPublicacionFecha")
                .then((response) => {
                    console.log(response.data);
                    this.libros = response.data;
                })
                .catch((error) => {
                    console.log("There was an error: " + error);
                });
        },
        onScroll() {
            const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop;
            if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 70) {
                return;
            }
            this.showElement = currentScrollPosition < this.lastScrollPosition;
            this.lastScrollPosition = currentScrollPosition;
        },
    },
    beforeDestroy() {
        window.removeEventListener("scroll", this.onScroll);
    },
    computed: {
        listOne() {
            return this.items.filter((item) => item.list === 1);
        },
        listTwo() {
            return this.items.filter((item) => item.list === 2);
        },
    },
    components: {
        carouselPelis
    }
}
</script>

<style>
.draggable-item {
    margin: 10px;
    padding: 10px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    cursor: grab;
}

.drop-zone {
    background-color: #eee;
    margin-bottom: 10px;
    padding: 10px;
}

.drag-el {
    background-color: #fff;
    margin-bottom: 10px;
    padding: 5px;
}

.zoom-enter-active,
.zoom-leave-active {
    transition: transform 0.5s ease-in-out;
}

.zoom-enter,
.zoom-leave-to {
    transform: scale(0);
}

.hidden {
    display: none;
}
</style>