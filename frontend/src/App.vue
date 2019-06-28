<template>
  <div
    id="app"
  >
    <notification-container></notification-container>
    <main class="main">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>

  import NotificationContainer from '@/components/NotificationContainer';

  export default {
  components: {
    NotificationContainer,
  },

  computed: {
    isHomepage() {
      return this.$route.path === '/';
    },
  },
};
</script>

<style lang="scss" src="@/assets/styles/style.scss"></style>

