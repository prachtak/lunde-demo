<template>
  <validation-provider
    tag="div"
    :vid="id"
    :rules="rules"
    :name="label.toLowerCase()"
    v-slot="{ classes, errors }"
  >
    <div class="custom-control custom-checkbox">
      <input
        type="checkbox"
        class="custom-control-input"
        :class="classes"
        :id="id"
        v-model="innerValue"
        @change="$emit('input', innerValue)"
      >
      <label
        class="custom-control-label"
        :for="id"
      >{{label}}</label>
      <div class="invalid-feedback">
        {{errors[0]}}
      </div>
    </div>
  </validation-provider>
</template>

<script>
  import {ValidationProvider} from 'vee-validate';

  export default {
  components: {
    ValidationProvider,
  },

  props: {
    id: String,
    label: String,
    value: Boolean,
    rules: [String, Object],
  },
  data() {
    return {
      innerValue: this.value,
    };
  },

  watch: {
    value(val) {
      this.innerValue = val;
    },
  },
};
</script>
