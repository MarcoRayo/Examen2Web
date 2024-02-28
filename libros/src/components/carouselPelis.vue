<template>
    <div>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1080"
        img-height="200"
        style="text-shadow: 1px 1px 2px #333;"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
        label-next="Siguiente"
        label-prev="Atras"
      >
        <b-carousel-slide img-src="https://picsum.photos/1080/200/?image=13"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1080/200/?image=8"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1080/200/?image=50"></b-carousel-slide>
      </b-carousel>

    </div>
  </template>

<script>
export default {
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    methods: {
      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      }
    }
  }
</script>

<style>

</style>