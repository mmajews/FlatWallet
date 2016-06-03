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
});