/**
 * Created by Marcin on 31.05.2016.
 */
app.factory('Authentication', function ($resource) {
    return $resource('/api', {}, {
        getSocialDetails: {
            method: 'GET',
            url: '/api/facebook/details'
        },

        getCurrent:{
            method: 'GET',
            url: '/api/user/current'
        }
    })
})