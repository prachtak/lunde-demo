<template>
  <notifications
    class="notify-container"
    :duration="10000"
    :max="6"
  >
    <template v-slot:body="{ item, data, close }">
      <div
        class="notify"
        :class="'notify-' + (item.type || DEFAULT_TYPE)"
        @click="close"
      >
        <i
          v-if="!item.data || !item.data.iconDisabled"
          class="material-icons notify-icon"
        >
          {{item | icon}}
        </i>
        <div class="notify-content">
          <div class="notify-title">
            {{item.title || item.text}}
          </div>
          <div
            v-if="item.title"
            class="notify-body"
            v-html="item.text"
          ></div>
        </div>
      </div>
    </template>
  </notifications>
</template>

<script>
const DEFAULT_TYPE = 'success';
const TYPE_ICON = {
  success: 'check',
  info: 'info',
  warning: 'warning',
  danger: 'error_outline',
  error: 'error_outline',
};

export default {
  filters: {
    icon: item => {
      if (item.data && item.data.icon) {
        return item.data.icon;
      }
      return TYPE_ICON[item.type] || TYPE_ICON[DEFAULT_TYPE];
    },
  },

  data() {
    return {
      DEFAULT_TYPE,
    };
  },
};
</script>
