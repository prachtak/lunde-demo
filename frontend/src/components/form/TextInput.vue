<template>
  <validation-provider
    tag="div"
    :vid="id"
    :rules="rules"
    :name="label.toLowerCase()"
    v-slot="{ errors }"
  >
    <mdb-input
      v-bind="$attrs"
      :id="id"
      :label="label"
      :validation="!!errors[0]"
      :isValid="!errors[0]"
      :invalidFeedback="errors[0]"
      v-model="innerValue"
      @input="$emit('input', innerValue)"
    />
  </validation-provider>
</template>

<script>
  import {ValidationProvider} from 'vee-validate';
  import {mdbInput} from 'mdbvue';

  export default {
  components: {
    ValidationProvider,
    mdbInput,
  },

  props: {
    id: String,
    label: String,
    value: String,
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
