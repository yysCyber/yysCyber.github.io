<template>
    <div>
        <PageHeader page-title="Aerial Photography Calculation Record" back-page-title="Aerial Photography Calculator" :back-routes="['/aerial-photography/calculator']"/>

    </div>
</template>

<script>
    import PageHeader from "../../../components/PageHeader";

    export default {
        name: "RecordInfo",

        created() {
            // TODO 读取相机
        },

        components: {
            PageHeader
        }
    }
</script>

<style scoped>

</style>