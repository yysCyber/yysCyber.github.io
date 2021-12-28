<template>
    <div>
        <el-page-header @back="navigationToIndexPage()" :title="backPageTitle" :content="pageTitle"/>
        <el-divider/>
    </div>
</template>

<script>
    export default {
        name: "PageHeader",

        props: {
            'pageTitle': String,
            'backRoutes': Array,
            'backPageTitle': String
        },

        methods: {
            navigationToIndexPage() {
                let res = true;
                let cur = this.$route.path;
                for (let r in this.backRoutes) {
                    if (r === cur) {
                        res = false;
                        break;
                    }
                }
                if (res) {
                    this.$router.push(this.backRoutes[0]);
                }
            }
        }
    }
</script>

<style scoped></style>