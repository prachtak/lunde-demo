<template>
  <div class="loader-wrapper">
    <div
      v-if="loading"
      class="d-flex justify-content-center align-items-center p-5"
      role="status"
    >
      <div class="spinner-grow spinner-grow-sm text-default">
        <span class="sr-only">Načítání...</span>
      </div>
      <div class="spinner-grow text-default">
        <span class="sr-only">Načítání...</span>
      </div>
      <div class="spinner-grow spinner-grow-sm text-default">
        <span class="sr-only">Načítání...</span>
      </div>
    </div>
    <slot v-else></slot>
  </div>
</template>

<script>
export default {
  props: {
    loading: Boolean,
  },
};
</script>

<style lang="scss" scoped>
.spinner-grow {
  width: 3rem;
  height: 3rem;

  &-sm {
    width: 1.5rem;
    height: 1.5rem;
  }

  &:nth-child(2) {
    animation: spinner-grow 0.75s linear 0.25s infinite;
  }
}
</style>
