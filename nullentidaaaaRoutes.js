const express = require('express');
const RolController = require('../controllers/entidaaaaController');

const router = express.Router();


router.get('/', entidaaaaController.getAll);
router.get('/:id', entidaaaaRolController.getById);
router.post('/', entidaaaaRolController.create);
router.put('/:id', entidaaaaRolController.update);
router.delete('/:id', entidaaaaRolController.delete);



module.exports = router;
