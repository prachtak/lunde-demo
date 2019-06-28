<template>
  <div class="contact-us">

    <loader :loading="isLoading">

      <div class="col-md-2">
        <div class="card card-primary">
          <div class="card-body">

            <select v-model="contactFormTypeCode">
              <option v-for="type in data" v-bind:value="type.code">
                {{ type.description }}
              </option>
            </select>

            <div class="contact-us">
              <validation-observer
                ref="contactUs"
                tag="form"
                novalidate
                @submit.prevent="submit"
              >

                <text-input
                  id="policyNumber"
                  label="Policy Number"
                  v-model="input.policyNumber"
                  rules="required"
                ></text-input>

                <text-input
                  id="name"
                  label="Name"
                  v-model="input.name"
                  rules="required"
                ></text-input>

                <text-input
                  id="surname"
                  label="Surname"
                  v-model="input.surname"
                  rules="required"
                ></text-input>

                <text-input
                  type="textarea"
                  id="request"
                  label="Your request"
                  v-model="input.requestMessage"
                  rules="required|max:150"
                ></text-input>

                <div class="text-center mt-4">
                  <btn
                    class="btn-c-primary"
                    type="submit"
                    :loading="isLoading"
                  >Odeslat
                  </btn>
                </div>

              </validation-observer>
            </div>
          </div>
        </div>
      </div>
    </loader>

    <mdb-modal
      :show="modal.contactUs"
      @close="modal.contactUs = false"
    >
      <mdb-modal-header>
        <mdb-modal-title>
          Kontaktní formulář
        </mdb-modal-title>
      </mdb-modal-header>
      <mdb-modal-body>
        <slot name="body">
          Message send
        </slot>
      </mdb-modal-body>
      <mdb-modal-footer>
        <btn
          class="btn-c-secondary"
          @click="modal.contactUs = false"
        >Zavřít</btn>
      </mdb-modal-footer>
    </mdb-modal>

  </div>
</template>

<script>

  import {mdbModal, mdbModalBody, mdbModalFooter, mdbModalHeader, mdbModalTitle} from 'mdbvue';
  import {contactUs, formResource} from '@/services/apiResource';
  import {ValidationObserver} from 'vee-validate';
  import TextInput from '@/components/form/TextInput';

  export default {

    components: {
      mdbModal,
      mdbModalHeader,
      mdbModalTitle,
      mdbModalBody,
      mdbModalFooter,
      ValidationObserver,
      TextInput,
    },
    data() {
      return {
        isLoading: false,
        data: [],
        modal: {
          contactUs: false,
        },
        input: {
          policyNumber: '',
          name: '',
          surname: '',
          requestMessage:'',
        },
        contactFormTypeCode: '',
      };
    },

    created() {
      this.loadData();
    },
    methods: {
      async loadData() {
        this.isLoading = true;
        this.data = await formResource.fetchTypes();
        this.contactFormTypeCode = this.data[0].code;
        this.isLoading = false;
      },

      async submit() {
        const isValid = await this.$refs.contactUs.validate();
        if (isValid) {
          const payload = {contactFormTypeCode: this.contactFormTypeCode, ...this.input };
          try {
            this.isLoading = true;
            await contactUs(payload);
            this.modal.contactUs = true;
            this.reset();
          } catch {
            this.$notify({
              text: 'Failed to send message, please try again later.',
              type: 'danger',
            });
          } finally {
            this.isLoading = false;
          }
        }
      },
      reset() {
        this.input = {
          name: '',
          mobile: '',
          email: '',
          message: '',
        };
        this.$refs.contactUs.reset();
        this.$refs.contactUs.$el.reset();
      },
    },
  };
</script>
