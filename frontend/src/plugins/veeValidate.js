import VeeValidate from 'vee-validate';

export default vue => {
  vue.use(VeeValidate, {
    classes: true,
    classNames: {
      invalid: 'is-invalid',
    },
    locale: 'en',
    dictionary: {
      cs: {
        messages: {
          required: fieldName => `Field ${fieldName} is required`,
          required_if: fieldName => `Field ${fieldName} is required`,
          max: (fieldName, params) => `Field ${fieldName} max char ${params}`,
        },
      },
    },
  });
};
