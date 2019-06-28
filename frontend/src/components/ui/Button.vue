<template>
  <component
    :is="tag"
    class="btn ripple-parent"
    :type="computedType"
    :disabled="loading || loadingRight"
    v-bind="$attrs"
    @click="$emit('click', $event)"
    @mousedown.native="handleWave"
    @mousedown="handleWave"
  >
    <span
      v-if="loading"
      class="spinner-border spinner-border-sm spinner-left"
      role="status"
      aria-hidden="true"
    ></span>
    <slot></slot>
    <span
      v-if="loadingRight"
      class="spinner-border spinner-border-sm spinner-right"
      role="status"
      aria-hidden="true"
    ></span>
  </component>
</template>

<script>
  import waves from 'mdbvue/src/mixins/waves';

  export default {
  mixins: [waves],

  props: {
    tag: {
      type: String,
      default: 'button',
    },
    type: {
      type: String,
      default: 'button',
    },
    loading: {
      type: Boolean,
      default: false,
    },
    loadingRight: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    computedType() {
      return this.tag === 'button' ? this.type : null;
    },
  },

  methods: {
    handleWave(e) {
      this.wave(e);
    },
  },
};
</script>

<style lang="scss" scoped>
.btn {
  .spinner-right {
    margin-left: 0.5rem;
  }
  .spinner-left {
    margin-right: 0.5rem;
  }
}
</style>
f
